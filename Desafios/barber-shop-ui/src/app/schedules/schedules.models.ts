export interface ScheduleAppointementMonthModel { // conter a lista
  year: number
  month: number
  scheduledAppointments: ClientScheduleAppointmentModel[]
}

export interface ClientScheduleAppointmentModel { // listagem
  id: number
  day: number
  startAt: Date
  endAt: Date
  clientId: number
  clientName: string
}

export interface SaveScheduleModel { // salvarr agendamento
  startAt?: Date
  endAt?: Date
  clientId?: number
}

export interface SelectClientModel {
  id: number
  name: string
}
