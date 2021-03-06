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

package edu.hku.sdb.connect;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ConnectionManager {

  public static Connection getConnection(String databaseUrl, String userName,
                                         String password) {
    ConnectionService connectionService = null;
    try {
      connectionService = (ConnectionService) Naming.lookup(databaseUrl);
    } catch (NotBoundException | MalformedURLException | RemoteException e) {
      e.printStackTrace();
    }
    try {
      return connectionService.getConnection();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Connection getConnection(String databaseUrl) {
    return null;
  }
}
