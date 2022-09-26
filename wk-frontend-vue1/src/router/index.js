import { createRouter, createWebHashHistory } from 'vue-router'
import Welcome from '@/components/Welcome'
import scootersOverview31 from '@/components/scooters/ScootersOverview31'
import Overview32 from '@/components/scooters/Overview32'
import UnknownRoute from '@/components/scooters/UnknownRoute'

const routes = [
  { path: '/', redirect: '/home' },
  {
    name: 'home',
    path: '/home',
    component: Welcome
  },
  {
    path: '/overview31',
    name: 'overview31',
    component: scootersOverview31
  },
  {
    path: '/overview32',
    name: 'overview32',
    component: Overview32
  },
  { path: '/:youCanRightWhatYouWant(.*)', component: UnknownRoute }

]

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
  linkActiveClass: 'active'
})
