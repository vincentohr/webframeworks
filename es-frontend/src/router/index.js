import { createRouter, createWebHashHistory } from 'vue-router'
import Welcome from '@/components/Welcome'
import scootersOverview31 from '@/components/scooters/ScootersOverview31'
import Overview32 from '@/components/scooters/Overview32'
import UnknownRoute from '@/components/scooters/UnknownRoute'
import Overview33 from '@/components/scooters/Overview33'
import Overview34 from '@/components/scooters/Overview34'
import Detail32 from '@/components/scooters/Detail32'
import Detail34 from '@/components/scooters/Detail34'
import Overview37 from '@/components/scooters/Overview37'
import Detail37 from '@/components/scooters/Detail37'
import SignIn from '@/components/SignIn'
import Overview45 from '@/components/trips/Overview45'
import Map45 from '@/components/scooters/Map45'

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    name: 'home',
    path: '/home',
    component: Welcome
  },
  {
    name: 'sign_in',
    path: '/sign-in',
    component: SignIn
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
      {
        name: 'overview33',
        path: '/scooters/overview33/:id',
        component: Detail32,
        props: true
      }
    ]
  },
  {
    path: '/scooters/overview34',
    name: 'overview34',
    component: Overview34,
    children: [
      {
        name: 'detail34',
        path: '/scooters/overview34/:id',
        component: Detail34,
        props: true
      }
    ]
  },
  {
    path: '/scooters/overview37',
    name: 'overview37',
    component: Overview37,
    children: [
      {
        name: 'detail37',
        path: '/scooters/overview37/:id',
        component: Detail37,
        props: true
      }
    ]
  },
  {
    path: '/trips/overview45/:finish?',
    name: 'overview45',
    component: Overview45
  },
  {
    path: '/trips/map45',
    name: 'Map45',
    component: Map45
  },
  {
    path: '/:notFound(.*)',
    component: UnknownRoute
  }
]

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
  linkActiveClass: 'active'
})
