import { InjectionToken } from "@angular/core";
import { IClientService } from "./api-client/clients/iclients.service";
import { ISnackbarManagerService } from "./isnackbar-manager.service";

export const SERVICES_TOKEN = {
  HTTP: {
    CLIENT: new InjectionToken<IClientService>('SERVICES_TOKEN.HTTP.CLIENT'),
    // SCHEDULE: new InjectionToken<IScheduleService>('SERVICES_TOKEN.HTTP.SCHEDULE')
  },
  SNACKBAR: new InjectionToken<ISnackbarManagerService>('SERVICES_TOKEN.SNACKBAR')
}
