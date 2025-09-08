/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.commons.bcel6.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.commons.bcel6.Const;

/**
 * This attribute exists for local or 
 * anonymous classes and ... there can be only one.
 * 
 * @since 6.0
 */
public class EnclosingMethod extends Attribute {

    // Pointer to the CONSTANT_Class_info structure representing the 
    // innermost class that encloses the declaration of the current class.
    private int classIndex;

    // If the current class is not immediately enclosed by a method or 
    // constructor, then the value of the method_index item must be zero.  
    // Otherwise, the value of the  method_index item must point to a 
    // CONSTANT_NameAndType_info structure representing the name and the 
    // type of a method in the class referenced by the class we point 
    // to in the class_index.  *It is the compiler responsibility* to 
    // ensure that the method identified by this index is the closest 
    // lexically enclosing method that includes the local/anonymous class.
    private int methodIndex;

    // Ctors - and code to read an attribute in.
    EnclosingMethod(int nameIndex, int len, DataInput input, ConstantPool cpool) throws IOException {
        this(nameIndex, len, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
    }

    private EnclosingMethod(int nameIndex, int len, int classIdx,int methodIdx, ConstantPool cpool) {
        super(Const.ATTR_ENCLOSING_METHOD, nameIndex, len, cpool);
        classIndex  = classIdx;
        methodIndex = methodIdx;
    }

    @Override
    public void accept(Visitor v) {
      v.visitEnclosingMethod(this);
    }

    @Override
    public Attribute copy(ConstantPool constant_pool) {
        return (Attribute) clone();
    }

    // Accessors
    public final int getEnclosingClassIndex() { return classIndex; }  
    public final int getEnclosingMethodIndex(){ return methodIndex;}

    public final void setEnclosingClassIndex(int idx) {classIndex = idx;}
    public final void setEnclosingMethodIndex(int idx){methodIndex= idx;}

    public final ConstantClass getEnclosingClass() {
        ConstantClass c = 
            (ConstantClass)super.getConstantPool().getConstant(classIndex,Const.CONSTANT_Class);
        return c;
    }

    public final ConstantNameAndType getEnclosingMethod() {
        if (methodIndex == 0) {
            return null;
        }
        ConstantNameAndType nat = 
            (ConstantNameAndType)super.getConstantPool().getConstant(methodIndex,Const.CONSTANT_NameAndType);
        return nat;
    }

    @Override
    public final void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(classIndex);
        file.writeShort(methodIndex);
    }    
}
