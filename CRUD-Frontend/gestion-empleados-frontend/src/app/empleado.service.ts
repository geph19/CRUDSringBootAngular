import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Empleado } from './empleado';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  //ENDPOINT obtiene una lista de los empleados en la base de datos
  private baseURL = "http://localhost:8080/api/v1/empleados";

  constructor(private httpClient:HttpClient) { }

  //Metodo que se encarga de traer los empleados
  obtenerListaEmpleados():Observable<Empleado[]>{
    return this.httpClient.get<Empleado[]>(`${this.baseURL}`)
  }

  //metodo para crear un empleado nuevo
  registrarEmpleado(empleado:Empleado): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, empleado)
  }

  //metodo para actualizar empleado
  actualizarEmpleado(id:number, empleado:Empleado): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, empleado)
  }

  //metodo para obtener user por id
  obtenerEmpleadoPorId(id:number):Observable<Empleado>{
    return this.httpClient.get<Empleado>(`${this.baseURL}/${id}`)
  }

  //metodo para eliminar empleado
  deleteEmpleado(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`)
  }
}
