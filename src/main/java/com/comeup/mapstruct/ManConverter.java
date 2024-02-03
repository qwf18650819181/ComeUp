package com.comeup.mapstruct;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @auth: qwf
 * @date: 2023年12月26日 0026
 * @description:
 * default：默认值，不使用任何依赖注入框架，MapStruct 将生成一个不带有任何依赖注入特性的普通类。
 *
 * spring：使用 Spring 作为依赖注入框架，MapStruct 将生成一个 Spring Bean，可以被 Spring 自动注入。
 *
 * cdi：使用 CDI（Contexts and Dependency Injection，上下文和依赖注入）作为依赖注入框架，MapStruct 将生成一个可以被 CDI 自动注入的 Bean。
 *
 * jsr330：使用 JSR-330 标准（依赖注入标准，被 Spring 和 CDI 等框架所支持）作为依赖注入框架，MapStruct 将生成一个带有 @Inject 注解的 Bean，可以被 JSR-330 兼容的框架自动注入。
 */
@Mapper(componentModel = "default", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManConverter {

    ManConverter INSTANCE = Mappers.getMapper(ManConverter.class);

    Main.Man toMan(Main.Human bo);

    @InheritConfiguration(name = "toMan")
    List<Main.Man> toMans(List<Main.Human> list);
}
