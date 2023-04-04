package com.slayerd.cases.idgenerator;

import com.slayerd.exceptions.IdGenerateFailureException;

/**
 * (what) generate a random id
 *
 * (why) we need a duplicate id
 *
 * (how) through the method generate()
 *
 * @author slayerd
 * @since 2023-04-02
 * @version 1.0
 */
public interface IdGenerator {
    String generate() throws IdGenerateFailureException;
}
