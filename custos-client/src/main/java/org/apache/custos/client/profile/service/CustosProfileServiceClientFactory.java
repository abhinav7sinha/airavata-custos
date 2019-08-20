/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.custos.client.profile.service;

import org.apache.custos.profile.iam.admin.services.cpi.IamAdminServices;
import org.apache.custos.profile.iam.admin.services.cpi.exception.IamAdminServicesException;
import org.apache.custos.profile.iam.admin.services.cpi.iam_admin_services_cpiConstants;
import org.apache.custos.profile.tenant.cpi.TenantProfileService;
import org.apache.custos.profile.tenant.cpi.exception.TenantProfileServiceException;
import org.apache.custos.profile.tenant.cpi.profile_tenant_cpiConstants;
import org.apache.custos.profile.user.cpi.UserProfileService;
import org.apache.custos.profile.user.cpi.exception.UserProfileServiceException;
import org.apache.custos.profile.user.cpi.profile_user_cpiConstants;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by goshenoy on 03/08/2017.
 */
public class CustosProfileServiceClientFactory {
    public static UserProfileService.Client createCustosUserProfileServiceClient(String serverHost, int serverPort)  throws UserProfileServiceException {
        try {
            TTransport transport = new TSocket(serverHost, serverPort);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol multiplexedProtocol = new TMultiplexedProtocol(protocol, profile_user_cpiConstants.USER_PROFILE_CPI_NAME);
            return new UserProfileService.Client(multiplexedProtocol);
        } catch (TTransportException e) {
            throw new UserProfileServiceException(e.getMessage());
        }
    }

    public static IamAdminServices.Client createCustosIamAdminServiceClient(String serverHost, int serverPort) throws IamAdminServicesException {
        try {
            TTransport transport = new TSocket(serverHost, serverPort);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol multiplexedProtocol = new TMultiplexedProtocol(protocol, iam_admin_services_cpiConstants.IAM_ADMIN_SERVICES_CPI_NAME);
            return new IamAdminServices.Client(multiplexedProtocol);
        } catch (TTransportException e) {
            throw new IamAdminServicesException(e.getMessage());
        }
    }

    public static TenantProfileService.Client createCustosTenantProfileServiceClient(String serverHost, int serverPort) throws TenantProfileServiceException {
        try {
            TTransport transport = new TSocket(serverHost, serverPort);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol multiplexedProtocol = new TMultiplexedProtocol(protocol, profile_tenant_cpiConstants.TENANT_PROFILE_CPI_NAME);
            return new TenantProfileService.Client(multiplexedProtocol);
        } catch (TTransportException e) {
            throw new TenantProfileServiceException(e.getMessage());
        }
    }
}