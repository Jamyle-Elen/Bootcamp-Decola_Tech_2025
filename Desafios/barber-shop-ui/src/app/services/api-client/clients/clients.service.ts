import { Injectable } from '@angular/core';
import { IClientService } from './iclients.service';
import { Observable } from 'rxjs';
import { DetailClientResponse, ListClientResponse, SaveClientRequest, SaveClientResponse, updateClientRequest, UpdateClientResponse } from './client.models';
import { HttpClient } from '@angular/common/http';
import { Environments } from '../../../../enviroments/environments';

@Injectable({
  providedIn: 'root'
})
export class ClientsService implements IClientService {

  private readonly basePath = Environments.apiUrl

  constructor(private http: HttpClient) { }

  save(request: SaveClientRequest): Observable<SaveClientResponse> {
    return this.http.post<SaveClientResponse>(`${this.basePath}clients`, request)
  }
  update(id: number, request: UpdateClientResponse): Observable<UpdateClientResponse> {
    return this.http.put<UpdateClientResponse>(`${this.basePath}clients/${id}`, request)
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.basePath}clients/${id}`)
  }
  list(): Observable<ListClientResponse[]> {
    return this.http.get<ListClientResponse[]>(`${this.basePath}clients`)
  }
  findById(id: number): Observable<DetailClientResponse> {
    return this.http.get<DetailClientResponse>(`${this.basePath}clients/${id}`)
  }
}
