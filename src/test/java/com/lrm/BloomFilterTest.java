package com.lrm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(),
                5000, 0.01);
        // 往过滤器中塞入数据
        filter.put(1);
        filter.put(2);
        filter.put(3);

        // 判断某一数据是否存在
        boolean mightContain = filter.mightContain(1);

        // mightContain = true  存在
        System.out.println(mightContain);
    }

}
