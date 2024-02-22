package com.Gestion.empleados.demo.controller;

import com.Gestion.empleados.demo.excepciones.ResourceNotFoundException;
import com.Gestion.empleados.demo.model.Empleado;
import com.Gestion.empleados.demo.repository.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @GetMapping("/empleados")
    public List<Empleado> listaEmpleados(){
        return empleadoRepositorio.findAll();
    }

    @PostMapping("/empleados")
    public Empleado saveEmpleado(@RequestBody Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> editarEmpleado(@PathVariable Long id){
        Empleado empleado = empleadoRepositorio.findById(id).orElseThrow(()-> new ResourceNotFoundException("El Empleado con el id: " + id + " no existe"));

        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado detallesEmpleado){
        Empleado empleado = empleadoRepositorio.findById(id).orElseThrow(()-> new ResourceNotFoundException("El Empleado con el id: " + id + " no existe"));

        empleado.setNombre(detallesEmpleado.getNombre());
        empleado.setApellido(detallesEmpleado.getApellido());
        empleado.setEmail(detallesEmpleado.getEmail());

        Empleado empleadoActualizado = empleadoRepositorio.save(empleado);

        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("empleados/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmpleado(@PathVariable Long id){
        Empleado empleado =  empleadoRepositorio.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("El Empleado con el id: \" + id + \" no existe"));

        empleadoRepositorio.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);

    }
}
