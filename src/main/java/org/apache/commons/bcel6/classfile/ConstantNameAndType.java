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

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.commons.bcel6.Constants;

/** 
 * This class is derived from the abstract {@link Constant}
 * and represents a reference to the name and signature
 * of a field or method.
 *
 * @version $Id$
 * @see     Constant
 */
public final class ConstantNameAndType extends Constant {

    private static final long serialVersionUID = -7913354727264034451L;
    private final int name_index; // Name of field/method
    private final int signature_index; // and its signature.


    /**
     * Initialize from another object.
     */
    public ConstantNameAndType(ConstantNameAndType c) {
        this(c.getNameIndex(), c.getSignatureIndex());
    }


    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException
     */
    ConstantNameAndType(DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }


    /**
     * @param name_index Name of field/method
     * @param signature_index and its signature
     */
    public ConstantNameAndType(int name_index, int signature_index) {
        super(Constants.CONSTANT_NameAndType);
        this.name_index = name_index;
        this.signature_index = signature_index;
    }


    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( Visitor v ) {
        v.visitConstantNameAndType(this);
    }


    /**
     * Dump name and signature index to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public final void dump( DataOutputStream file ) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(name_index);
        file.writeShort(signature_index);
    }


    /**
     * @return Name index in constant pool of field/method name.
     */
    public final int getNameIndex() {
        return name_index;
    }


    /** @return name
     */
    public final String getName( ConstantPool cp ) {
        return cp.constantToString(getNameIndex(), Constants.CONSTANT_Utf8);
    }


    /**
     * @return Index in constant pool of field/method signature.
     */
    public final int getSignatureIndex() {
        return signature_index;
    }


    /** @return signature
     */
    public final String getSignature( ConstantPool cp ) {
        return cp.constantToString(getSignatureIndex(), Constants.CONSTANT_Utf8);
    }


    /**
     * @return String representation
     */
    @Override
    public final String toString() {
        return super.toString() + "(name_index = " + name_index + ", signature_index = "
                + signature_index + ")";
    }
}
