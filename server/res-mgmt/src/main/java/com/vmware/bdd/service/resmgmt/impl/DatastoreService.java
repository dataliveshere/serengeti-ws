/***************************************************************************
 * Copyright (c) 2012-2013 VMware, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package com.vmware.bdd.service.resmgmt.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.bdd.apitypes.Datastore.DatastoreType;
import com.vmware.bdd.apitypes.DatastoreAdd;
import com.vmware.bdd.apitypes.DatastoreRead;
import com.vmware.bdd.apitypes.DatastoreReadDetail;
import com.vmware.bdd.dal.IClusterDAO;
import com.vmware.bdd.dal.IDatastoreDAO;
import com.vmware.bdd.entity.VcDatastoreEntity;
import com.vmware.bdd.exception.BddException;
import com.vmware.bdd.exception.VcProviderException;
import com.vmware.bdd.service.resmgmt.IDatastoreService;
import com.vmware.bdd.service.resmgmt.IResourceService;

@Service
public class DatastoreService implements IDatastoreService {
   private static final Logger logger = Logger
         .getLogger(DatastoreService.class);

   private IDatastoreDAO dsDao;

   private IClusterDAO clusterDao;

   private IResourceService resService;


   public IDatastoreDAO getDsDao() {
      return dsDao;
   }

   @Autowired
   public void setDsDao(IDatastoreDAO dsDao) {
      this.dsDao = dsDao;
   }

   public IClusterDAO getClusterDao() {
      return clusterDao;
   }

   @Autowired
   public void setClusterDao(IClusterDAO clusterDao) {
      this.clusterDao = clusterDao;
   }

   public IResourceService getResService() {
      return resService;
   }

   @Autowired
   public void setResService(IResourceService resManager) {
      this.resService = resManager;
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#getAllSharedDatastores()
    */
   @Override
   @Transactional(readOnly = true)
   public Set<String> getAllSharedDatastores() {
      return getAllDatastoresByType(DatastoreType.SHARED);
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#getSharedDatastoresByNames(java.util.List)
    */
   @Override
   @Transactional(readOnly = true)
   public Set<String> getSharedDatastoresByNames(List<String> nameList) {
      if (nameList == null) {
         return null;
      }
      Set<String> result = new HashSet<String>();
      for (String name : nameList) {
         result.addAll(getAllDatastoresByTypeAndName(DatastoreType.SHARED, name));
      }
      return result;
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#getLocalDatastoresByNames(java.util.List)
    */
   @Override
   @Transactional(readOnly = true)
   public Set<String> getLocalDatastoresByNames(List<String> nameList) {
      if (nameList == null) {
         return null;
      }
      Set<String> result = new HashSet<String>();
      for (String name : nameList) {
         result.addAll(getAllDatastoresByTypeAndName(DatastoreType.LOCAL, name));
      }
      return result;
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#getAllLocalDatastores()
    */
   @Override
   @Transactional(readOnly = true)
   public Set<String> getAllLocalDatastores() {
      return getAllDatastoresByType(DatastoreType.LOCAL);
   }


   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#getDatastoresByName(java.lang.String)
    */
   @Override
   @Transactional(readOnly = true)
   public Set<String> getDatastoresByName(String name) {
      List<VcDatastoreEntity> datastores = dsDao.findByName(name);
      return getDatastorePattern(datastores);
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#getDatastoresByNameList(java.util.List)
    */
   @Override
   @Transactional(readOnly = true)
   public Set<String> getDatastoresByNameList(List<String> nameList) {
      if (nameList == null) {
         return null;
      }
      Set<String> result = new HashSet<String>();
      for (String name : nameList) {
         result.addAll(getDatastoresByName(name));
      }
      return result;
   }


   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#addDataStores(com.vmware.bdd.apitypes.DatastoreAdd)
    */
   @Override
   @Transactional
   public void addDataStores(DatastoreAdd datastore) {
      addDatastoreEntity(datastore.getType(), datastore.getSpec(),
            datastore.getName());
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#addDataStores(java.lang.String, com.vmware.bdd.apitypes.Datastore.DatastoreType, java.util.List)
    */
   @Override
   @Transactional
   public void addDataStores(String name, DatastoreType type, List<String> spec) {
      addDatastoreEntity(type, spec, name);
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#getAllDataStoreName()
    */
   @Override
   @Transactional(readOnly = true)
   public Set<String> getAllDataStoreName() {
      List<VcDatastoreEntity> datastores = dsDao.findAllSortByName();
      Set<String> result = new HashSet<String>();
      for (VcDatastoreEntity ds : datastores) {
         result.add(ds.getName());
      }
      return result;
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#getDatastoreRead(java.lang.String)
    */
   @Override
   @Transactional(readOnly = true)
   public DatastoreRead getDatastoreRead(String name) {
      logger.debug("get datastore " + name);
      List<VcDatastoreEntity> entities = dsDao.findByName(name);
      if (entities.isEmpty()) {
         return null;
      }

      DatastoreRead read = new DatastoreRead();
      read.setName(name);
      read.setType(entities.get(0).getType());
      read.setDatastoreReadDetails(new ArrayList<DatastoreReadDetail>());
      for (VcDatastoreEntity entity : entities) {
         DatastoreReadDetail detail = new DatastoreReadDetail();
         detail.setVcDatastoreName(entity.getVcDatastore());
         read.getDatastoreReadDetails().add(detail);
      }
      logger.debug("found datastore: " + read);
      return read;
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#getAllDatastoreReads()
    */
   @Override
   @Transactional(readOnly = true)
   public List<DatastoreRead> getAllDatastoreReads() {
      logger.debug("get all datastores.");
      List<VcDatastoreEntity> entities = dsDao.findAllSortByName();
      List<DatastoreRead> result = new ArrayList<DatastoreRead>();
      if (entities.isEmpty()) {
         return result;
      }

      DatastoreRead read = new DatastoreRead();
      read.setName(entities.get(0).getName());
      read.setType(entities.get(0).getType());
      read.setDatastoreReadDetails(new ArrayList<DatastoreReadDetail>());
      result.add(read);
      for (VcDatastoreEntity entity : entities) {
         if (!entity.getName().equals(read.getName())) {
            // new datastore
            read = new DatastoreRead();
            read.setName(entity.getName());
            read.setType(entity.getType());
            read.setDatastoreReadDetails(new ArrayList<DatastoreReadDetail>());
            result.add(read);
         }
         DatastoreReadDetail detail = new DatastoreReadDetail();
         detail.setVcDatastoreName(entity.getVcDatastore());
         read.getDatastoreReadDetails().add(detail);
      }
      logger.debug("found datastores: " + result);
      return result;
   }

   /* (non-Javadoc)
    * @see com.vmware.bdd.service.impl.IDataStoreService#deleteDatastore(java.lang.String)
    */
   @Override
   @Transactional
   public void deleteDatastore(String name) {
      logger.debug("delete datastore " + name);
      final List<VcDatastoreEntity> entities = dsDao.findByName(name);
      if (entities.isEmpty()) {
         throw VcProviderException.DATASTORE_NOT_FOUND(name);
      }

      final Set<String> patterns = new HashSet<String>();
      for (VcDatastoreEntity entity : entities) {
         patterns.add(entity.getVcDatastore());
      }
      List<String> clusterNames =
            getClusterDao().findClustersByUsedDatastores(patterns);
      if (!clusterNames.isEmpty()) {
         logger.error("cannot remove datastore, since following clusters referenced this datastore: "
               + clusterNames);
         throw VcProviderException
               .DATASTORE_IS_REFERENCED_BY_CLUSTER(clusterNames);
      }


      for (VcDatastoreEntity entity : entities) {
         dsDao.delete(entity);
      }
      logger.debug("successfully deleted datastore " + name);
   }


   private Set<String> getAllDatastoresByType(DatastoreType type) {
      // load vc resource pools
      List<VcDatastoreEntity> datastores = dsDao.findByType(type);
      Set<String> result = getDatastorePattern(datastores);
      logger.debug("got " + type + " datastores: " + result);
      return result;
   }

   private Set<String> getAllDatastoresByTypeAndName(DatastoreType type,
         String name) {
      // load vc resource pools
      List<VcDatastoreEntity> datastores = dsDao.findByNameAndType(type, name);
      Set<String> result = getDatastorePattern(datastores);
      logger.debug("got datastores for type : " + type + ", name: " + name
            + result);
      return result;
   }


   private Set<String> getDatastorePattern(List<VcDatastoreEntity> datastores) {
      Set<String> result = new HashSet<String>();
      for (VcDatastoreEntity store : datastores) {
         result.add(store.getVcDatastore());
      }
      return result;
   }

   private void addDatastoreEntity(final DatastoreType type,
         final List<String> datastores, final String name) {
      if (dsDao.nameExisted(name)) {
         throw BddException.ALREADY_EXISTS("datastore", name);
      }
      for (String ds : datastores) {
         if (!resService.isDatastoreExistInVC(ds)) {
            throw VcProviderException.DATASTORE_NOT_FOUND(ds);
         }
         VcDatastoreEntity entity = new VcDatastoreEntity();
         entity.setType(type);
         entity.setName(name);
         entity.setVcDatastore(ds);
         dsDao.insert(entity);
         logger.info("add shared datastore " + ds);
      }
   }
}
