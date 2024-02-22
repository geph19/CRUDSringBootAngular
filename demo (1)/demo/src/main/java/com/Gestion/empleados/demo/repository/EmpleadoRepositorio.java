package com.Gestion.empleados.demo.repository;

import com.Gestion.empleados.demo.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
}
