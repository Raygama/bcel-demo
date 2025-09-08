/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.commons.bcel6.classfile;

import org.apache.commons.bcel6.Const;

/**
 * Super class for all objects that have modifiers like private, final, ...
 * I.e. classes, fields, and methods.
 *
 * @version $Id$
 */
public abstract class AccessFlags {

    /**
     * @deprecated (since 6.0) will be made private; do not access directly, use getter/setter
     */
    @java.lang.Deprecated
    protected int access_flags; // TODO not used externally at present


    public AccessFlags() {
    }


    /**
     * @param a inital access flags
     */
    public AccessFlags(int a) {
        access_flags = a;
    }


    /** 
     * @return Access flags of the object aka. "modifiers".
     */
    public final int getAccessFlags() {
        return access_flags;
    }


    /** 
     * @return Access flags of the object aka. "modifiers".
     */
    public final int getModifiers() {
        return access_flags;
    }


    /** Set access flags aka "modifiers".
     * @param access_flags Access flags of the object. 
     */
    public final void setAccessFlags( int access_flags ) {
        this.access_flags = access_flags;
    }


    /** Set access flags aka "modifiers".
     * @param access_flags Access flags of the object. 
     */
    public final void setModifiers( int access_flags ) {
        setAccessFlags(access_flags);
    }


    private void setFlag( int flag, boolean set ) {
        if ((access_flags & flag) != 0) { // Flag is set already
            if (!set) {
                access_flags ^= flag;
            }
        } else { // Flag not set
            if (set) {
                access_flags |= flag;
            }
        }
    }


    public final void isPublic( boolean flag ) {
        setFlag(Const.ACC_PUBLIC, flag);
    }


    public final boolean isPublic() {
        return (access_flags & Const.ACC_PUBLIC) != 0;
    }


    public final void isPrivate( boolean flag ) {
        setFlag(Const.ACC_PRIVATE, flag);
    }


    public final boolean isPrivate() {
        return (access_flags & Const.ACC_PRIVATE) != 0;
    }


    public final void isProtected( boolean flag ) {
        setFlag(Const.ACC_PROTECTED, flag);
    }


    public final boolean isProtected() {
        return (access_flags & Const.ACC_PROTECTED) != 0;
    }


    public final void isStatic( boolean flag ) {
        setFlag(Const.ACC_STATIC, flag);
    }


    public final boolean isStatic() {
        return (access_flags & Const.ACC_STATIC) != 0;
    }


    public final void isFinal( boolean flag ) {
        setFlag(Const.ACC_FINAL, flag);
    }


    public final boolean isFinal() {
        return (access_flags & Const.ACC_FINAL) != 0;
    }


    public final void isSynchronized( boolean flag ) {
        setFlag(Const.ACC_SYNCHRONIZED, flag);
    }


    public final boolean isSynchronized() {
        return (access_flags & Const.ACC_SYNCHRONIZED) != 0;
    }


    public final void isVolatile( boolean flag ) {
        setFlag(Const.ACC_VOLATILE, flag);
    }


    public final boolean isVolatile() {
        return (access_flags & Const.ACC_VOLATILE) != 0;
    }


    public final void isTransient( boolean flag ) {
        setFlag(Const.ACC_TRANSIENT, flag);
    }


    public final boolean isTransient() {
        return (access_flags & Const.ACC_TRANSIENT) != 0;
    }


    public final void isNative( boolean flag ) {
        setFlag(Const.ACC_NATIVE, flag);
    }


    public final boolean isNative() {
        return (access_flags & Const.ACC_NATIVE) != 0;
    }


    public final void isInterface( boolean flag ) {
        setFlag(Const.ACC_INTERFACE, flag);
    }


    public final boolean isInterface() {
        return (access_flags & Const.ACC_INTERFACE) != 0;
    }


    public final void isAbstract( boolean flag ) {
        setFlag(Const.ACC_ABSTRACT, flag);
    }


    public final boolean isAbstract() {
        return (access_flags & Const.ACC_ABSTRACT) != 0;
    }


    public final void isStrictfp( boolean flag ) {
        setFlag(Const.ACC_STRICT, flag);
    }


    public final boolean isStrictfp() {
        return (access_flags & Const.ACC_STRICT) != 0;
    }


    public final void isSynthetic( boolean flag ) {
        setFlag(Const.ACC_SYNTHETIC, flag);
    }


    public final boolean isSynthetic() {
        return (access_flags & Const.ACC_SYNTHETIC) != 0;
    }


    public final void isAnnotation( boolean flag ) {
        setFlag(Const.ACC_ANNOTATION, flag);
    }


    public final boolean isAnnotation() {
        return (access_flags & Const.ACC_ANNOTATION) != 0;
    }


    public final void isEnum( boolean flag ) {
        setFlag(Const.ACC_ENUM, flag);
    }


    public final boolean isEnum() {
        return (access_flags & Const.ACC_ENUM) != 0;
    }
}
