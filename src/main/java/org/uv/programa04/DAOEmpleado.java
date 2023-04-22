/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yahir
 */
public class DAOEmpleado implements IDAOGeneral<Empleado, Long> {

    @Override
    public Empleado create(Empleado p) {
        ConexionDB cx = ConexionDB.getInstance();
        TransaccionDB tdb = new TransaccionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "insert into empleados (clave, nombre, direccion, telefono) values (?,?,?,?);";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setLong(1, p.getClave());
                    pst.setString(2, p.getNombre());
                    pst.setString(3, p.getDireccion());
                    pst.setString(4, p.getTelefono());
                    pst.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res = cx.execute(tdb);
        if (res) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se guardo");
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Error...");
        }
        return p;
    }

    @Override

    public boolean delete(Long id) {
        ConexionDB cx = ConexionDB.getInstance();
        TransaccionDB tdb = new TransaccionDB<Long>(id) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "delete from empleados where clave = ?;";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1,id.toString());
                    pst.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res = cx.execute(tdb);
        if (res) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se elimino");
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Error...");
        }
        return res;
    }

    @Override
    public Empleado update(Empleado p, Long id) {
        ConexionDB cx = ConexionDB.getInstance();
        TransaccionDB tdb = new TransaccionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "update empleados set nombre = ?, direccion = ?, telefono = ? where clave = ?;";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, p.getNombre());
                    pst.setString(2, p.getDireccion());
                    pst.setString(3, p.getTelefono());
                    pst.setString(4, id.toString());
                    pst.executeUpdate();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res = cx.execute(tdb);
        if (res) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se modifico");
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Error...");
        }
        return p;
    }

    @Override
    public List<Empleado> findAll() {
        ConexionDB cx = ConexionDB.getInstance();
        List<Empleado> listaEmpleado = new ArrayList<>();
        TransaccionDB tdb = new TransaccionDB(cx) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "select * from empleados;";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet res = pst.executeQuery();
                    while (res.next()){
                        Empleado emp = new Empleado();
                        emp.setClave(res.getLong("clave"));
                        emp.setNombre(res.getString("nombre"));
                        emp.setDireccion(res.getString("direccion"));
                        emp.setTelefono(res.getString("telefono"));
                        listaEmpleado.add(emp);
                    }
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
                boolean res = cx.execute(tdb);
        if (res) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "se modifico...");
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "error ...");
        }
        return listaEmpleado;
    }

    @Override
    public Empleado findById(Long id) {
        Empleado emp = new Empleado();
        ConexionDB cx = ConexionDB.getInstance();
        TransaccionDB tdb=new TransaccionDB<Long>(id) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql="SELECT * FROM ejemplo WHERE clave=?;";
                    
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, id.toString());
                    ResultSet res=pst.executeQuery();
                    
                    while (res.next()){
                        emp.setClave(res.getLong("clave"));
                        emp.setNombre(res.getString("nombre"));
                        emp.setDireccion(res.getString("direccion"));
                        emp.setTelefono(res.getString("telefono"));
                        
                    }
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res = cx.execute(tdb);
        if (res) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Información:");
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "error ...");
        }
        return emp;
    
    }

}
