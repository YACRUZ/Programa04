/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package org.uv.programa04;

/**
 *
 * @author yahir
 */
public class Programa04 {

    public static void main(String[] args) {
        DAOEmpleado daoempleado = new DAOEmpleado();
        Empleado emp = new Empleado();
//        emp.setClave(9);
//        emp.setNombre("Jesus");
//        emp.setDireccion("Huatusco");
//        emp.setTelefono("273-100");
//        
//        daoempleado.delete(Long.parseLong("10"));
//        daoempleado.create(emp);
//
//        emp.setNombre("Miguel");
//        emp.setDireccion("PADELMA");
//        emp.setTelefono("273-100");
//        
//        daoempleado.update(emp, Long.valueOf(9));

          daoempleado.findById(Long.parseLong("9"));
    }
}
