import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { R8Meuserservice2SharedModule } from 'app/shared/shared.module';

import { LogsComponent } from './logs.component';

import { logsRoute } from './logs.route';

@NgModule({
  imports: [R8Meuserservice2SharedModule, RouterModule.forChild([logsRoute])],
  declarations: [LogsComponent]
})
export class LogsModule {}
