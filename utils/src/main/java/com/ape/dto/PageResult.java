package com.ape.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 * Data transfer object when response a page result.
 *
 * AngryApe created at 2017-10-23
 */
public class PageResult<T extends Collection> extends Result implements Serializable {

    private int page;

    private int pageSize;

    private int total;



}
