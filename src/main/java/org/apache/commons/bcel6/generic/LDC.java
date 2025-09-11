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
package org.apache.commons.bcel6.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.commons.bcel6.ExceptionConstants;
import org.apache.commons.bcel6.util.ByteSequence;

/** 
 * LDC - Push item from constant pool.
 *
 * <PRE>Stack: ... -&gt; ..., item</PRE>
 *
 * @version $Id$
 */
public class LDC extends CPInstruction implements PushInstruction, ExceptionThrower {

    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     */
    LDC() {
    }


    public LDC(int index) {
        super(org.apache.commons.bcel6.Constants.LDC_W, index);
        setSize();
    }


    // Adjust to proper size
    protected final void setSize() {
        if (super.getIndex() <= org.apache.commons.bcel6.Constants.MAX_BYTE) { // Fits in one byte?
            super.setOpcode(org.apache.commons.bcel6.Constants.LDC);
            length = 2;
        } else {
            super.setOpcode(org.apache.commons.bcel6.Constants.LDC_W);
            length = 3;
        }
    }


    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    @Override
    public void dump( DataOutputStream out ) throws IOException {
        out.writeByte(opcode);
        if (length == 2) {
            out.writeByte(super.getIndex());
        } else {
            out.writeShort(super.getIndex());
        }
    }


    /**
     * Set the index to constant pool and adjust size.
     */
    @Override
    public final void setIndex( int index ) {
        super.setIndex(index);
        setSize();
    }


    /**
     * Read needed data (e.g. index) from file.
     */
    @Override
    protected void initFromFile( ByteSequence bytes, boolean wide ) throws IOException {
        length = 2;
        super.setIndex(bytes.readUnsignedByte());
    }


    public Object getValue( ConstantPoolGen cpg ) {
        org.apache.commons.bcel6.classfile.Constant c = cpg.getConstantPool().getConstant(super.getIndex());
        switch (c.getTag()) {
            case org.apache.commons.bcel6.Constants.CONSTANT_String:
                int i = ((org.apache.commons.bcel6.classfile.ConstantString) c).getStringIndex();
                c = cpg.getConstantPool().getConstant(i);
                return ((org.apache.commons.bcel6.classfile.ConstantUtf8) c).getBytes();
            case org.apache.commons.bcel6.Constants.CONSTANT_Float:
                return new Float(((org.apache.commons.bcel6.classfile.ConstantFloat) c).getBytes());
            case org.apache.commons.bcel6.Constants.CONSTANT_Integer:
                return Integer.valueOf(((org.apache.commons.bcel6.classfile.ConstantInteger) c).getBytes());
            case org.apache.commons.bcel6.Constants.CONSTANT_Class:
                int nameIndex = ((org.apache.commons.bcel6.classfile.ConstantClass) c).getNameIndex();
                c = cpg.getConstantPool().getConstant(nameIndex);
                return new ObjectType(((org.apache.commons.bcel6.classfile.ConstantUtf8) c).getBytes());
            default: // Never reached
                throw new RuntimeException("Unknown or invalid constant type at " + super.getIndex());
        }
    }


    @Override
    public Type getType( ConstantPoolGen cpg ) {
        switch (cpg.getConstantPool().getConstant(super.getIndex()).getTag()) {
            case org.apache.commons.bcel6.Constants.CONSTANT_String:
                return Type.STRING;
            case org.apache.commons.bcel6.Constants.CONSTANT_Float:
                return Type.FLOAT;
            case org.apache.commons.bcel6.Constants.CONSTANT_Integer:
                return Type.INT;
            case org.apache.commons.bcel6.Constants.CONSTANT_Class:
                return Type.CLASS;
            default: // Never reached
                throw new RuntimeException("Unknown or invalid constant type at " + super.getIndex());
        }
    }


    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConstants.createExceptions(ExceptionConstants.EXCS.EXCS_STRING_RESOLUTION);
    }


    /**
     * Call corresponding visitor method(s). The order is:
     * Call visitor methods of implemented interfaces first, then
     * call methods according to the class hierarchy in descending order,
     * i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( Visitor v ) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitLDC(this);
    }
}
