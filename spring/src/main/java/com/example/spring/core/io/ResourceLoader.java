package com.example.spring.core.io;

/**
 * 资源加载器接口
 *
 * @author ryan
 * @date 2023/2/26 13:27
 */
public interface ResourceLoader {
    Resource getResource(String location);
}
