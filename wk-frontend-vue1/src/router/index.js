import { createRouter, createWebHashHistory } from 'vue-router'
import Welcome from '@/components/Welcome'
import scootersOverview31 from '@/components/scooters/ScootersOverview31'
import Overview32 from '@/components/scooters/Overview32'
import UnknownRoute from '@/components/scooters/UnknownRoute'
import Overview33 from '@/components/scooters/Overview33'
import Overview34 from '@/components/scooters/Overview34'
import Detail32 from '@/components/scooters/Detail32'
import Detail34 from '@/components/scooters/Detail34'

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
  {
    path: '/scooters/overview33',
    name: 'overview33',
    component: Overview33,
    children: [
      { name: 'overview33', path: '/scooters/overview33/:id', component: Detail32, props: true }
    ]
  },
  {
    path: '/scooters/overview34',
    name: 'overview34',
    component: Overview34,
    children: [
      { name: 'overview34', path: '/scooters/overview34/:id', component: Detail34, props: true }
    ]
  },
  { path: '/:notFound(.*)', component: UnknownRoute }
]

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
  linkActiveClass: 'active'
})
