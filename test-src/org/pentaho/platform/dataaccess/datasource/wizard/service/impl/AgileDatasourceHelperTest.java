package org.pentaho.platform.dataaccess.datasource.wizard.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.pentaho.database.model.DatabaseAccessType;
import org.pentaho.database.model.IDatabaseType;
import org.pentaho.database.model.DatabaseType;
import org.pentaho.database.service.IDatabaseDialectService;
import org.pentaho.platform.api.engine.IPluginResourceLoader;


public class AgileDatasourceHelperTest {

  @Test
  public void testGetAgileMartDatasource() {
    IPluginResourceLoader resLoader = mock(IPluginResourceLoader.class);
    when( resLoader.getResourceAsStream( any( Class.class ), anyString() ) ).thenReturn( new InputStream() {
      @Override
      public int read() throws IOException {
        throw new IOException();
      }
    } );
    AgileMartDatasourceHelper helper = new AgileMartDatasourceHelper( resLoader );
    IDatabaseDialectService databaseDialectService = mock( IDatabaseDialectService.class );
    List<IDatabaseType> databaseTypes = new LinkedList<IDatabaseType> ();
    List<DatabaseAccessType> types = new LinkedList<DatabaseAccessType> ();
    types.add( DatabaseAccessType.NATIVE );
    types.add( DatabaseAccessType.JNDI );
    types.add( DatabaseAccessType.ODBC );
    DatabaseType dbType = new DatabaseType("MONETDB", "monetdb", types, 5192, "" );
    databaseTypes.add( dbType );
    when( databaseDialectService.getDatabaseTypes() ).thenReturn( databaseTypes );
    helper.getAgileMartDatasource( databaseDialectService );
  }
}
