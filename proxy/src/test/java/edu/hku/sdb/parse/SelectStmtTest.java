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

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import edu.hku.sdb.catalog.SdbColumnKey;
import edu.hku.sdb.catalog.Type;
import org.junit.Test;

import edu.hku.sdb.parse.BinaryPredicate.BinOperator;

/**
 * Case with Null statement is not tested.
 */
public class SelectStmtTest {

  /**
   * Prepare a selection statement without group-by.
   *
   * @return a selection statement
   */
  private SelectStmt createSelStmtWithoutGroup() {
    SelectionList selectList = new SelectionList();
    selectList.getItemList().add(
            new SelectionItem(new FieldLiteral("T1", "a", Type.INT), ""));

    List<TableRef> tblRefs = new ArrayList<TableRef>();

    BaseTableRef tbl1 = new BaseTableRef("T1", "T11");
    tbl1.setJoinOp(JoinOperator.INNER_JOIN);
    FieldLiteral left = new FieldLiteral("T1", "id", Type.INT, true,
            new SdbColumnKey("1", "2"));
    FieldLiteral right = new FieldLiteral("T2", "id", Type.INT, true,
            new SdbColumnKey("2", "3"));
    Expr onClause = new NormalBinPredicate(BinOperator.EQ, left, right);

    BaseTableRef tbl2 = new BaseTableRef("T2", "");
    tbl2.setJoinOp(JoinOperator.INNER_JOIN);
    tbl2.setLeftTblRef(tbl1);
    tbl2.setOnClause(onClause);

    tblRefs.add(tbl1);
    tblRefs.add(tbl2);

    Expr whereClause = new NormalBinPredicate(BinOperator.EQ, new FieldLiteral(
            "T1", "b", Type.INT), new FieldLiteral("T2", "c", Type.INT));

    SelectStmt stmt = new SelectStmt();
    stmt.setSelectList(selectList);
    stmt.setTableRefs(tblRefs);
    stmt.setWhereClause(whereClause);

    stmt.setP(new BigInteger("7"));
    stmt.setQ(new BigInteger("5"));
    stmt.setN(new BigInteger("35"));
    stmt.setG(new BigInteger("2"));

    return stmt;
  }

  @Test
  public void testEqual() {
    SelectStmt stmt1 = createSelStmtWithoutGroup();
    SelectStmt stmt2 = createSelStmtWithoutGroup();
    assertEquals(stmt1, stmt2);
  }
}
