/******************************************************************************
 *   Copyright (c) 2012-2013 VMware, Inc. All Rights Reserved.
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 ******************************************************************************/
package com.vmware.bdd.utils;


public class Constants {
   public static final String CLUSTER_CONFIG_FORMAT_ERROR =
         "The format of configuration is invalid !";
   public static final String CLUSTER_CONFIG_TYPE_ERROR =
         "The configuration is invalid ! At present, we only support ";
   public static final String LIST_CONFIG_ERROR =
         "The blacklist or whitelist config error!";
   public static final String PRACK_NO_TOPOLOGY_TYPE_SPECIFIED =
         "Rack Policy in spec file will not benefit Hadoop running when not specify topology through CLI. By default, Hadoop cluster will use NONE topology.";
   public static final String FAIR_SCHEDULER_FILE_NAME = "fair-scheduler.xml";
   public static final String FAIR_SCHEDULER_FILE_ATTRIBUTE = "text";

   public static final int VHM_PROTOCOL_VERSION = 3;
   public static final String SET_MANUAL_ELASTICITY_INFO_VERSION = "version";
   public static final String SET_MANUAL_ELASTICITY_INFO_CLUSTER_NAME = "cluster_name";
   public static final String SET_MANUAL_ELASTICITY_INFO_JOBTRACKER = "jobtracker";
   public static final String SET_MANUAL_ELASTICITY_INFO_INSTANCE_NUM = "instance_num";
   public static final String SET_MANUAL_ELASTICITY_INFO_NODE_GROUPS = "node_groups";
   public static final String SET_MANUAL_ELASTICITY_INFO_SERENGETI_INSTANCE =
         "serengeti_instance";
   public static final String SET_MANUAL_ELASTICITY_INFO_RECEIVE_ROUTE_KEY =
         "route_key";
   public static final String SET_MANUAL_ELASTICITY_INFO_ACTION = "action";
   public static final String CLUSTER_NAME_PATTERN = "[a-zA-Z0-9_]+";
   public static final String NODE_GROUP_NAME_PATTERN = "[[a-zA-Z0-9]+]{1,80}";
   public static final String NAME_PATTERN = "[a-zA-Z0-9_\\-\\s]+";
   public static final String SERENGETI_SERVER_VM_MOBID = "vim.cms_moref";
   public static final String GUEST_VARIABLE_DNS_KEY_1 = "dnsserver1";
   public static final String GUEST_VARIABLE_DNS_KEY_0 = "dnsserver0";
   public static final String GUEST_VARIABLE_HOSTNAME_KEY = "hostname";
   public static final String GUEST_VARIABLE_GATEWAY_KEY = "gateway";
   public static final String GUEST_VARIABLE_NETMASK_KEY = "netmask";
   public static final String GUEST_VARIABLE_IP_KEY = "ipaddr";
   public static final String GUEST_VARIABLE_POLICY_KEY = "bootproto";
   public static final String GUEST_VARIABLE_BOOTUP_UUID = "bootupUUID";
   public static final String GUEST_VARIABLE_PORT_GROUP = "portgroup";
   public static final String GUEST_VARIABLE_NIC_DEVICES = "nics";

   public static final String NULL_IP = "0.0.0.0";
   public static final String IP_V4_PATTERN = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

   public static final String NODE_ACTION_CLONING_VM = "Cloning VM";
   public static final String NODE_ACTION_CLONING_FAILED = "Cloning VM failed";
   public static final String NODE_ACTION_WAITING_IP = "Waiting for ip";
   public static final String NODE_ACTION_RECONFIGURE = "Reconfiging VM";
   public static final String NODE_ACTION_GET_IP_FAILED =
         "Failed to get VM ip address";
   public static final int VM_POWER_ON_WAITING_SEC = 600;
   public static final int VM_SHUTDOWN_WAITING_SEC = 600;
   public static final int VM_FAST_SHUTDOWN_WAITING_SEC = 180;
   public static final String PORT_GROUP_NAME_PATTERN =
         "[a-zA-Z0-9_\\-\\s\\.]+";
   public static final String VC_DATASTORE_NAME_PATTERN =
         "[a-zA-Z0-9_\\-\\s\\?\\*\\.\\(\\)]+";
   public static final String WRONG_NUM_OF_JOBTRACKER =
         "The number of JobTrackers cannot exceed one.";
   public static final String WRONG_NUM_OF_RESOURCEMANAGER =
         "The number of ResourceManagers cannot exceed one.";
   public static final String WRONG_NUM_OF_ZOOKEEPER =
         "The number of ZooKeepers cannot be less than three.";
   public static final String ODD_NUM_OF_ZOOKEEPER =
         "The number of ZooKeepers is recommended to be an odd number.";
   public static final String WORKER_CLIENT_HA_FLAG =
         "The worker or client node group has vSphere High Availability enabled.";
   public static final String REGISONSERVER_DATANODE_SEPERATION =
         "RegionServer and DataNode are separated, which might degrade performance.";
   public static final String NOT_DEFINED_ROLE =
         "Some roles are unidentified.";
   public static final String WRONG_NUM_OF_JOURNALNODE =
         "The number of journal nodes must be three or more.";
   public static final String ODD_NUM_OF_JOURNALNODE =
         "The number of journal nodes is recommended to be an odd number.";
   public static final String NAMENODE_AUTO_FAILOVER_ZOOKEEPER =
         "To support vSphere High Availability on name nodes, you must configure a ZooKeeper group.";
   public static final String NO_NAMENODE_HA =
         "vSphere High Availability is not enabled for NameNode, but journal nodes appear.";
   public static final String WRONG_NUM_OF_NODEGROUPS =
         "Only one node group can include JobTracker, ZooKeeper or HMaster.";
   public static final String WRONG_NUM_OF_WORKERNODES = "No worker exists.";
   public static final String DATA_CLIENT_NODE_JOURNALNODE_COEXIST =
         "Journal nodes cannot be on data nodes or client nodes.";
   public static final String STORAGE_TYPE_ALLOWED =
         "Valid storage types are SHARED, LOCAL, and TEMPFS.";
   public static final String TEMPFS_NOT_ALLOWED =
         "TEMPFS storage can be set only on compute nodes with strict association to data nodes.";
   public static final String DEFAULT_VENDOR = "Apache";
   public static final String CDH_VENDOR = "CDH";
   public static final String GPHD_VENDOR = "GPHD";
   public static final String HDP_VENDOR = "HDP";
   public static final String MAPR_VENDOR = "MAPR";
   public static final String PHD_VENDOR = "PHD";
   public static final String CURRENT_DISTRO_CAN_NOT_SUPPORT_HDFS2 =
         "The current Hadoop distribution does not support HDFS2 High Availability or federation.";
   public static final String CDH4_PATTERN = "4\\.[1-9]([0-9]){0,1}(\\.\\d){0,2}";
   public static final String IP_PATTERN = 
         "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";

   // rabbitMQ message format
   public static final String ERROR_CODE_FIELD = "error_code";
   public static final String ERROR_MSG_FIELD = "error_msg";
   public static final String FINISH_FIELD = "finished";
   public static final String SUCCEED_FIELD = "succeed";
   public static final String PROGRESS_FIELD = "progress";
   public static final String PROGRESS_MESSAGE_FIELD = "progress_msg";
   public static final String CLUSTER_DATA_FIELD = "cluster_data";
   public static final String CLUSTER_NAME_FIELD = "name";
   public static final String GROUP_FIELD = "groups";
   public static final String GROUP_NAME_FIELD = "name";
   public static final String INSTANCE_FIELD = "instances";
   public static final String INSTANCE_NAME_FIELD = "name";
   public static final String INSTANCE_STATUS_FIELD = "status";
   public static final String INSTANCE_ACTION_FIELD = "action";
   public static final String INSTANCE_IP_FIELD = "ip_address";

   // cloud provider
   public static final String CLOUD_PROVIDER_NAME_FIELD = "name";
   public static final String CLOUD_PROVIDER_TYPE_FIELD = "type";
   public static final String VSPHERE_PROVIDER_NAME = "static";
   public static final String VSPHERE_PROVIDER_TYPE = "VC";
   public static final String HA_FLAG_ON = "on";
   public static final String HA_FLAG_FT = "ft";

   public static final String ELASTIC_RUNTIME_AUTOMATION_ENABLE = "elastic_runtime.automation.enable";
   public static final String MUST_CONFIGURE_FQDN =
         "You cannot deploy a CDH 4.2.1+ cluster in a network without FQDN/IP.";
   public static final String CONVERTED_MEMORY_DIVISIBLE_BY_4 =
         "The size of the virtual machine memory must be evenly divisible by 4. ";

   public static final String ROOT_SNAPSTHOT_NAME = "serengeti-snapshot";
   public static final String ROOT_SNAPSTHOT_DESC = "Serengeti template Root Snapshot";

   public static final int DEFAULT_SCHEDULER_POOL_SIZE = 1024;
   public static final int MIN_MEM_SIZE = 1024;
   public static final String VERSION = "1.0.0";

   public static final String SSH_PRIVATE_KEY_FILE_NAME = "/home/serengeti/.ssh/id_rsa";
   public static final String SSH_PRIVATE_KEY_CONFIG_NAME = "serengeti.ssh.private.key.file";
   public static final int SSH_PORT = 22;
   public static final int SSH_SESSION_TIMEOUT = 15000;
   public static final String SET_PASSWORD_SCRIPT_CONFIG_NAME = "serengeti.set.password.script";
   public static final String SSH_USER_CONFIG_NAME = "serengeti.ssh.user";
   public static final String DEFAULT_SSH_USER_NAME = "serengeti";
   public static final String SSH_PORT_CONFIG_NAME = "serengeti.ssh.port";
   public static final int DEFAULT_SSH_PORT = 22;
   public static final String DEFAULT_SET_PASSWORD_SCRIPT = "/usr/sbin/serengeti-password";
}
