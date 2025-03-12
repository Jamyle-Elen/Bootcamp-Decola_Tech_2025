export interface SaveClientRequest {
  name: string;
  email: string;
  phone: string;
}

export interface updateClientRequest {
  name: string;
  email: string;
  phone: string;
}

export interface SaveClientResponse {
  id: number;
  name: string;
  email: string;
  phone: string;
}

export interface UpdateClientResponse {
  id: number;
  name: string;
  email: string;
  phone: string;
}

export interface ListClientResponse {
  id: number;
  name: string;
  email: string;
  phone: string;
}

export interface DetailClientResponse {
  id: number;
  name: string;
  email: string;
  phone: string;
}
