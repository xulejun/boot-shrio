package com.xlj.util;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @Author XuLeJun
 * @Date 2020/7/26 16:34
 *
 * 自定义salt，实现序列化接口
 */

public class SaltBySource extends SimpleByteSource implements Serializable {

    public SaltBySource(String string) {
        super(string);
    }

}
