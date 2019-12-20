import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { R8Meuserservice2SharedModule } from 'app/shared/shared.module';

import { JhiHealthCheckComponent } from './health.component';
import { JhiHealthModalComponent } from './health-modal.component';

import { healthRoute } from './health.route';

@NgModule({
  imports: [R8Meuserservice2SharedModule, RouterModule.forChild([healthRoute])],
  declarations: [JhiHealthCheckComponent, JhiHealthModalComponent],
  entryComponents: [JhiHealthModalComponent]
})
export class HealthModule {}
