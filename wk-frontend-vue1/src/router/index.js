import { createRouter, createWebHashHistory } from 'vue-router'
import Welcome from '@/components/Welcome'
import scootersOverview31 from '@/components/scooters/ScootersOverview31'
import Overview32 from '@/components/scooters/Overview32'
import UnknownRoute from '@/components/scooters/UnknownRoute'
import Overview33 from '@/components/scooters/Overview33'
import Detail32 from '@/components/scooters/Detail32'

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
  { path: '/:youCanRightWhatYouWant(.*)', component: UnknownRoute },
  {
    path: '/scooters/overview33',
    name: 'overview33',
    component: Overview33,
    children: [
      { name: 'overview33', path: '/scooters/overview33/:id', component: Detail32, props: true }
    ]
  }

]

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
  linkActiveClass: 'active'
})
