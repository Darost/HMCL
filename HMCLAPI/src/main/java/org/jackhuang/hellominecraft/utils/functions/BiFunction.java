/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jackhuang.hellominecraft.utils.functions;

/**
 *
 * @author huangyuhui
 */
public interface BiFunction<A, B, C> {
    C apply(A a, B b);
}