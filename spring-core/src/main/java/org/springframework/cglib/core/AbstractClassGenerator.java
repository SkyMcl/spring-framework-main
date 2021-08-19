/*
 * Copyright 2003,2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cglib.core;

import java.lang.ref.WeakReference;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.springframework.asm.ClassReader;
import org.springframework.cglib.core.internal.Function;
import org.springframework.cglib.core.internal.LoadingCache;

/**
 * Abstract class for all code-generating CGLIB utilities.
 * In addition to caching generated classes for performance, it provides hooks for
 * customizing the <code>ClassLoader</code>, name of the generated class, and transformations
 * applied before generation.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
abstract public class AbstractClassGenerator<T> implements ClassGenerator {

	private static final ThreadLocal CURRENT = new ThreadLocal();

	private static volatile Map<ClassLoader, ClassLoaderData> CACHE = new WeakHashMap<ClassLoader, ClassLoaderData>();

	private static final boolean DEFAULT_USE_CACHE =
			Boolean.parseBoolean(System.getProperty("cglib.useCache", "true"));


	private GeneratorStrategy strategy = DefaultGeneratorStrategy.INSTANCE;

	private NamingPolicy namingPolicy = DefaultNamingPolicy.INSTANCE;

	private Source source;

	private ClassLoader classLoader;

	private Class contextClass;

	private String namePrefix;

	private Object key;

	private boolean useCache = DEFAULT_USE_CACHE;

	private String className;

	private boolean attemptLoad;


	protected static class ClassLoaderData {

		private final Set<String> reservedClassNames = new HashSet<String>();

		/**
		 * {@link AbstractClassGenerator} here holds "cache key" (e.g. {@link org.springframework.cglib.proxy.Enhancer}
		 * configuration), and the value is the generated class plus some additional values
		 * (see {@link #unwrapCachedValue(Object)}.
		 * <p>The generated classes can be reused as long as their classloader is reachable.</p>
		 * <p>Note: the only way to access a class is to find it through generatedClasses cache, thus
		 * the key should not expire as long as the class itself is alive (its classloader is alive).</p>
		 */
		private final LoadingCache<AbstractClassGenerator, Object, Object> generatedClasses;

		/**
		 * Note: ClassLoaderData object is stored as a value of {@code WeakHashMap<ClassLoader, ...>} thus
		 * this classLoader reference should be weak otherwise it would make classLoader strongly reachable
		 * and alive forever.
		 * Reference queue is not required since the cleanup is handled by {@link WeakHashMap}.
		 */
		private final WeakReference<ClassLoader> classLoader;

		private final Predicate uniqueNamePredicate = new Predicate() {
			public boolean evaluate(Object name) {
				return reservedClassNames.contains(name);
			}
		};

		private static final Function<AbstractClassGenerator, Object> GET_KEY = new Function<AbstractClassGenerator, Object>() {
			public Object apply(AbstractClassGenerator gen) {
				return gen.key;
			}
		};

		public ClassLoaderData(ClassLoader classLoader) {
			if (classLoader == null) {
				throw new IllegalArgumentException("classLoader == null is not yet supported");
			}
			//创建弱引用队列追踪classLoader的回收情况
			this.classLoader = new WeakReference<ClassLoader>(classLoader);
			Function<AbstractClassGenerator, Object> load =
					new Function<AbstractClassGenerator, Object>() {
						public Object apply(AbstractClassGenerator gen) {
							//生成class
							Class klass = gen.generate(ClassLoaderData.this);
							return gen.wrapCachedClass(klass);
						}
					};
			//保存加载缓存 key和value都是函数式接口
			generatedClasses = new LoadingCache<AbstractClassGenerator, Object, Object>(GET_KEY, load);
		}

		public ClassLoader getClassLoader() {
			return classLoader.get();
		}

		public void reserveName(String name) {
			reservedClassNames.add(name);
		}

		public Predicate getUniqueNamePredicate() {
			return uniqueNamePredicate;
		}

		public Object get(AbstractClassGenerator gen, boolean useCache) {
			if (!useCache) {
				return gen.generate(ClassLoaderData.this);
			}
			else {
				//获取缓存值
				Object cachedValue = generatedClasses.get(gen);
				return gen.unwrapCachedValue(cachedValue);
			}
		}
	}


	protected T wrapCachedClass(Class klass) {
		return (T) new WeakReference(klass);
	}

	protected Object unwrapCachedValue(T cached) {
		return ((WeakReference) cached).get();
	}


	protected static class Source {

		String name;

		public Source(String name) {
			this.name = name;
		}
	}


	protected AbstractClassGenerator(Source source) {
		this.source = source;
	}

	protected void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	final protected String getClassName() {
		return className;
	}

	private void setClassName(String className) {
		this.className = className;
	}

	private String generateClassName(Predicate nameTestPredicate) {
		return namingPolicy.getClassName(namePrefix, source.name, key, nameTestPredicate);
	}

	/**
	 * Set the <code>ClassLoader</code> in which the class will be generated.
	 * Concrete subclasses of <code>AbstractClassGenerator</code> (such as <code>Enhancer</code>)
	 * will try to choose an appropriate default if this is unset.
	 * <p>
	 * Classes are cached per-<code>ClassLoader</code> using a <code>WeakHashMap</code>, to allow
	 * the generated classes to be removed when the associated loader is garbage collected.
	 * @param classLoader the loader to generate the new class with, or null to use the default
	 */
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	// SPRING PATCH BEGIN
	public void setContextClass(Class contextClass) {
		this.contextClass = contextClass;
	}
	// SPRING PATCH END

	/**
	 * Override the default naming policy.
	 * @param namingPolicy the custom policy, or null to use the default
	 * @see DefaultNamingPolicy
	 */
	public void setNamingPolicy(NamingPolicy namingPolicy) {
		if (namingPolicy == null)
			namingPolicy = DefaultNamingPolicy.INSTANCE;
		this.namingPolicy = namingPolicy;
	}

	/**
	 * @see #setNamingPolicy
	 */
	public NamingPolicy getNamingPolicy() {
		return namingPolicy;
	}

	/**
	 * Whether use and update the static cache of generated classes
	 * for a class with the same properties. Default is <code>true</code>.
	 */
	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}

	/**
	 * @see #setUseCache
	 */
	public boolean getUseCache() {
		return useCache;
	}

	/**
	 * If set, CGLIB will attempt to load classes from the specified
	 * <code>ClassLoader</code> before generating them. Because generated
	 * class names are not guaranteed to be unique, the default is <code>false</code>.
	 */
	public void setAttemptLoad(boolean attemptLoad) {
		this.attemptLoad = attemptLoad;
	}

	public boolean getAttemptLoad() {
		return attemptLoad;
	}

	/**
	 * Set the strategy to use to create the bytecode from this generator.
	 * By default an instance of {@link DefaultGeneratorStrategy} is used.
	 */
	public void setStrategy(GeneratorStrategy strategy) {
		if (strategy == null)
			strategy = DefaultGeneratorStrategy.INSTANCE;
		this.strategy = strategy;
	}

	/**
	 * @see #setStrategy
	 */
	public GeneratorStrategy getStrategy() {
		return strategy;
	}

	/**
	 * Used internally by CGLIB. Returns the <code>AbstractClassGenerator</code>
	 * that is being used to generate a class in the current thread.
	 */
	public static AbstractClassGenerator getCurrent() {
		return (AbstractClassGenerator) CURRENT.get();
	}

	public ClassLoader getClassLoader() {
		ClassLoader t = classLoader;
		if (t == null) {
			t = getDefaultClassLoader();
		}
		if (t == null) {
			t = getClass().getClassLoader();
		}
		if (t == null) {
			t = Thread.currentThread().getContextClassLoader();
		}
		if (t == null) {
			throw new IllegalStateException("Cannot determine classloader");
		}
		return t;
	}

	abstract protected ClassLoader getDefaultClassLoader();

	/**
	 * Returns the protection domain to use when defining the class.
	 * <p>
	 * Default implementation returns <code>null</code> for using a default protection domain. Sub-classes may
	 * override to use a more specific protection domain.
	 * </p>
	 * @return the protection domain (<code>null</code> for using a default)
	 */
	protected ProtectionDomain getProtectionDomain() {
		return null;
	}

	protected Object create(Object key) {
		try {
			//获取类加载器
			ClassLoader loader = getClassLoader();
			//设置缓存 key 是类加载器 classLoader value = ClassLoaderData 一个保存两个函数式接口引用的缓存
			Map<ClassLoader, ClassLoaderData> cache = CACHE;
			//从缓存内获取
			ClassLoaderData data = cache.get(loader);
			if (data == null) {
				//缓存内为空则 重新生成 此处加锁 保持唯一性
				synchronized (AbstractClassGenerator.class) {
					//此处双重判断 以免 并发造成数据重复 覆盖 类似双重判断的 单例模式
					cache = CACHE;
					data = cache.get(loader);
					//缓存内为空
					if (data == null) {
						//新建一个 缓存map =WeakMap 弱哈希map 当key失效之后 自动删除 map内的相关数据 继承自abstractMap
						Map<ClassLoader, ClassLoaderData> newCache = new WeakHashMap<ClassLoader, ClassLoaderData>(cache);
						//新建ClassLoaderData对象
						data = new ClassLoaderData(loader);
						//缓存内加入数据
						newCache.put(loader, data);
						CACHE = newCache;
					}
				}
			}
			//设置全局的key
			this.key = key;
			//获取缓存
			Object obj = data.get(this, getUseCache());
			if (obj instanceof Class) {
				//第一次创建对象
				return firstInstance((Class) obj);
			}
			//当obj不是class类型的则进行类型转换 再次创建对象
			return nextInstance(obj);
		}
		catch (RuntimeException | Error ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new CodeGenerationException(ex);
		}
	}

	protected Class generate(ClassLoaderData data) {
		Class gen;
		Object save = CURRENT.get();
		CURRENT.set(this);
		try {
			//获取类加载器
			ClassLoader classLoader = data.getClassLoader();
			if (classLoader == null) {
				throw new IllegalStateException("ClassLoader is null while trying to define class " +
						getClassName() + ". It seems that the loader has been expired from a weak reference somehow. " +
						"Please file an issue at cglib's issue tracker.");
			}
			synchronized (classLoader) {
				//生成代理类的名字
				String name = generateClassName(data.getUniqueNamePredicate());
				//保存代理类的名称
				data.reserveName(name);
				//设置代理类的名称
				this.setClassName(name);
			}
			//如果尝试加载为true
			if (attemptLoad) {
				try {
					//通过加载器 加载类
					gen = classLoader.loadClass(getClassName());
					return gen;
				}
				catch (ClassNotFoundException e) {
					// ignore
				}
			}
			//获取代理类的二进制文件
			byte[] b = strategy.generate(this);
			//获取类名
			String className = ClassNameReader.getClassName(new ClassReader(b));
			//设置保护域
			ProtectionDomain protectionDomain = getProtectionDomain();
			synchronized (classLoader) { // just in case
				// SPRING PATCH BEGIN
				// 将类文件加入到内存内
				gen = ReflectUtils.defineClass(className, b, classLoader, protectionDomain, contextClass);
				// SPRING PATCH END
			}
			return gen;
		}
		catch (RuntimeException | Error ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new CodeGenerationException(ex);
		}
		finally {
			CURRENT.set(save);
		}
	}

	abstract protected Object firstInstance(Class type) throws Exception;

	abstract protected Object nextInstance(Object instance) throws Exception;

}
