/*
 * Created on May 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.postgresql.core.types;

import java.math.BigDecimal;
import java.sql.Types;

import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

/**
 * @author davec
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PGInteger implements PGType
{
    private Integer val;
    
    public PGInteger( Integer x )
    {
        val = x;
    }
    
    public PGType castToServerType( int targetType ) throws PSQLException
    {
        try
        {
            switch ( targetType )
            {
            case Types.BOOLEAN:
            case Types.BIT:
                return new PGBoolean( val.intValue() == 0?Boolean.FALSE:Boolean.TRUE );
            case Types.REAL:
                return new PGFloat( new Float( val.floatValue() ) );
            case Types.DOUBLE:
            case Types.FLOAT:
                return new PGDouble(new Double( val.doubleValue() ));
            case Types.VARCHAR:
                return new PGString( toString() );
            case Types.SMALLINT:
            case Types.TINYINT:
                return new PGShort( new Short( val.shortValue() ));
            case Types.INTEGER:
                return this;
            case Types.DECIMAL:
            case Types.NUMERIC:
                return new PGBigDecimal( new BigDecimal( val.toString()));
            default:
                return new PGUnknown(val);
            }
        }
        catch( Exception ex )
        {
            throw new PSQLException("Error", new PSQLState(""),ex);
        }
    }
    public String toString()
    {
        return val.toString();
    }
}