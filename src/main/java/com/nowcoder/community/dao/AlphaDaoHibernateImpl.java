package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author 尚郑
 */
@Repository("alphaHibernate") //自定义bean名字
public class AlphaDaoHibernateImpl implements AlphaDao{

    @Override
    public String select() {
        return "Hibernate";
    }
}
