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

import org.apache.commons.bcel6.ExceptionConstants;

/**
 * Super class for instructions dealing with array access such as IALOAD.
 *
 * @version $Id$
 */
public abstract class ArrayInstruction extends Instruction implements ExceptionThrower,
        TypedInstruction {

    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     */
    ArrayInstruction() {
    }


    /**
     * @param opcode of instruction
     */
    protected ArrayInstruction(short opcode) {
        super(opcode, (short) 1);
    }


    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConstants.createExceptions(ExceptionConstants.EXCS.EXCS_ARRAY_EXCEPTION);
    }


    /** @return type associated with the instruction
     */
    @Override
    public Type getType( ConstantPoolGen cp ) {
        final short _opcode = super.getOpcode();
        switch (_opcode) {
            case org.apache.commons.bcel6.Constants.IALOAD:
            case org.apache.commons.bcel6.Constants.IASTORE:
                return Type.INT;
            case org.apache.commons.bcel6.Constants.CALOAD:
            case org.apache.commons.bcel6.Constants.CASTORE:
                return Type.CHAR;
            case org.apache.commons.bcel6.Constants.BALOAD:
            case org.apache.commons.bcel6.Constants.BASTORE:
                return Type.BYTE;
            case org.apache.commons.bcel6.Constants.SALOAD:
            case org.apache.commons.bcel6.Constants.SASTORE:
                return Type.SHORT;
            case org.apache.commons.bcel6.Constants.LALOAD:
            case org.apache.commons.bcel6.Constants.LASTORE:
                return Type.LONG;
            case org.apache.commons.bcel6.Constants.DALOAD:
            case org.apache.commons.bcel6.Constants.DASTORE:
                return Type.DOUBLE;
            case org.apache.commons.bcel6.Constants.FALOAD:
            case org.apache.commons.bcel6.Constants.FASTORE:
                return Type.FLOAT;
            case org.apache.commons.bcel6.Constants.AALOAD:
            case org.apache.commons.bcel6.Constants.AASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Oops: unknown case in switch" + _opcode);
        }
    }
}
