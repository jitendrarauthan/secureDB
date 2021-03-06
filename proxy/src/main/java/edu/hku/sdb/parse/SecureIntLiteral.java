/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.hku.sdb.parse;

import edu.hku.sdb.crypto.SDBEncrypt;

import java.math.BigInteger;

public class SecureIntLiteral extends LiteralExpr {

  private BigInteger secureInteger;

  public SecureIntLiteral(BigInteger secureInteger) {
    this.secureInteger = secureInteger;
  }

  public BigInteger getSecureInteger() {
    return secureInteger;
  }


  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SecureIntLiteral))
      return false;

    return secureInteger.equals(((SecureIntLiteral) obj).secureInteger);
  }


  /* (non-Javadoc)
   * @see edu.hku.sdb.parse.ParseNode#toSql()
   */
  @Override
  public String toSql() {
    return "\"" + SDBEncrypt.getSecureString(secureInteger) + "\"";
  }

  /* (non-Javadoc)
   * @see edu.hku.sdb.parse.Expr#involveSdbCol()
   */
  @Override
  public boolean involveEncrytedCol() {
    return false;
  }

  @Override
  public EncryptionScheme getEncrytionScheme() {
    return null;
  }

}
