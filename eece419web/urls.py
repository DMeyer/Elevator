from django.conf.urls.defaults import *
from django.views.generic.simple import *
from django.shortcuts import render_to_response

from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # (r'', include(admin.site.urls)),
    (r'^$', 'bosslift.views.index'),
    (r'^login/$', 'django.contrib.auth.views.login', {'template_name': 'bosslift/login.html'}),
    (r'^logout/$', 'bosslift.views.logout_view'),
    #(r'^inject1$', 'bosslift.views.inject_passenger'),


    (r'^inject2$', 'bosslift.views.inject2'),
    #(r'^inject$', direct_to_template, {'template': 'bosslift/inject.html'}),

    (r'^index2$', direct_to_template, {'template': 'bosslift/log.html'}),

    (r'^foot.htm$','bosslift.views.foot'),
    (r'^chart.html$','bosslift.views.chart'),
    (r'^simulation.html$','bosslift.views.simulation'),
    (r'^simulationtable.html$','bosslift.views.simulationtable'),
    (r'^bosslift/elevatorstatusdb/$','bosslift.views.elevatorstatusdb'),
    (r'^bosslift/elevatorpositiondb/$','bosslift.views.elevatorpositiondb'),
    (r'^elevatorstatus.html$','bosslift.views.elevatorstatus'),
    (r'^elevatorposition.html$','bosslift.views.elevatorposition'),
    (r'^bosslift/connection/$','bosslift.views.connection'),
    (r'^table0$', direct_to_template, {'template': 'bosslift/table0.html'}),
    (r'^bosslift/$', 'bosslift.views.index'),
    (r'^update_log$', 'bosslift.views.update_log'),
    (r'^bosslift/rxrequest/$', 'bosslift.views.rxrequest'),
    (r'^admin/', include(admin.site.urls)),
)
