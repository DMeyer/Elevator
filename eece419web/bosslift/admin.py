from bosslift.models import RxRequest, ConnectionSetup, ElevatorStatus, ElevatorPosition
from django.contrib import admin

class RxAdmin(admin.ModelAdmin):
    fields = ['date', 'request']
    list_display = ('id', 'request', 'date')
    list_filter = ['date']
    
admin.site.register(RxRequest, RxAdmin)
admin.site.register(ConnectionSetup)
admin.site.register(ElevatorStatus)
admin.site.register(ElevatorPosition)
