import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { R8Meuserservice2SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [R8Meuserservice2SharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class R8Meuserservice2HomeModule {}
