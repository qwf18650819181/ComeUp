package com.comeup.shiying;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NsyExcelProperty {

    /**
     * The name of the sheet header.
     *
     * <p>
     * write: It automatically merges when you have more than one head
     * <p>
     * read: When you have multiple heads, take the first one
     *
     * @return The name of the sheet header
     */
    String[] value() default {""};

    /**
     * Index of column
     * <p>
     * Read or write it on the index of column,If it's equal to -1, it's sorted by Java class.
     * <p>
     * priority: index &gt; order &gt; default sort
     *
     * @return Index of column
     */
    int index() default -1;

    /**
     * Defines the sort order for an column.
     * <p>
     * priority: index &gt; order &gt; default sort
     *
     * @return Order of column
     */
    int order() default Integer.MAX_VALUE;


}
