package com.ape.spring;

import com.ape.spring.entity.Formula;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * AngryApe created at 2017-11-15
 */
public class SpringTest {

    private ApplicationContext context;
    private EhCacheCacheManager cacheManager;

    private void init() {
        context = new ClassPathXmlApplicationContext("spring-context.xml");
        cacheManager = (EhCacheCacheManager) context.getBean("cacheManager");
    }

    public SpringTest() {
        init();
    }

    public static void main(String[] args) {
        SpringTest test = new SpringTest();
        EhCacheCache cache = (EhCacheCache) test.cacheManager.getCache("ape");
        Formula formula = new Formula();
        formula.setPointId("1");
        formula.setFormula("add");
        formula.setVariableList(new String[]{"dsadsad","d","sad","s"});
        cache.put("1", formula);
        System.out.println(cache.get("1").get());
        formula.setPointId("2");
        cache.put("1",formula);
        System.out.println(cache.get("1").get());

    }
}
