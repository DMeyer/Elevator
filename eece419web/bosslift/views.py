from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import get_object_or_404, render_to_response
from bosslift.models import RxRequest, ConnectionSetup, ElevatorStatus, ElevatorPosition
import datetime
from django.views.decorators.csrf import csrf_exempt
from django.views.decorators.csrf import requires_csrf_token
from django.template import RequestContext
import httplib, urllib
from django.contrib.auth.decorators import login_required
from django.contrib.auth import logout
from django.http import HttpResponseRedirect
from django.utils import simplejson

@csrf_exempt
@login_required(login_url='/login/')
def index(request):
    row =ConnectionSetup.objects.all().order_by('-id')[:1].get()
    address = getattr(row, 'address')
    n_of_elevators = getattr(row, 'elevators')
    n_of_floors = getattr(row, 'floors')

    return render_to_response('bosslift/index.html', {'address': address, 'n_of_elevators': n_of_elevators,
                                                      'n_of_floors': n_of_floors, 'floors': range(n_of_floors+1),
                                                      'elevators': range(n_of_elevators),},
                                                      context_instance=RequestContext(request))



def logout_view(request):
    logout(request)
    return HttpResponseRedirect('/')

@csrf_exempt
def connection(request):
    connection_address = request.POST['address']
    connection_elevators = request.POST['elevators']
    connection_floors = request.POST['floors']

    row2 = ElevatorPosition.objects.all()
    row2.delete();
    d = ElevatorPosition(E0='0', E1='0', E2='0', E3='0', E4='0', E5='0', E6='0', E7='0', E8='0', E9='0');
    d.save();

    p = ConnectionSetup(address=connection_address, elevators=connection_elevators, floors=connection_floors, date=datetime.datetime.now());
    p.save();
    return HttpResponse("Connection has been setup.")


# LOG
@csrf_exempt
def rxrequest(request):
    rx_post_request = request.POST['request']
    # rx_post_date = request.POST['date']
    rx_post_date = datetime.datetime.now()
    
    p = RxRequest(request=rx_post_request, date=datetime.datetime.now());
    p.save();
    return HttpResponse("Log has been received")

def inject_passenger(request):
    return render_to_response('bosslift/inject.html')

def update_log(request):
    latest_rxrequest_list = RxRequest.objects.all().order_by('-id')[:20]
    return render_to_response('bosslift/foot.htm', {'latest_rxrequest_list': latest_rxrequest_list})

@csrf_exempt
def inject2(request):
    #return render_to_response('bosslift/inject2.html')
    return render_to_response('bosslift/inject2.html', context_instance=RequestContext(request))

def foot(request):
    latest_rxrequest_list = RxRequest.objects.all().order_by('-id')[:20]
    return render_to_response('bosslift/foot.htm', {'latest_rxrequest_list': latest_rxrequest_list})

@csrf_exempt
def chart(request):
    row = ConnectionSetup.objects.all().order_by('-id')[:1].get()
    address = getattr(row, 'address')
    return render_to_response('bosslift/chart.html', {'address': address})

def simulation(request):
    row0 =ConnectionSetup.objects.all().order_by('-id')[:1].get()
    n_of_elevators = getattr(row0, 'elevators')

    row = ElevatorPosition.objects.all().order_by('-id')[:1].get()
    E0 = getattr(row, 'E0')
    E1 = getattr(row, 'E1')
    E2 = getattr(row, 'E2')
    E3 = getattr(row, 'E3')
    E4 = getattr(row, 'E4')
    E5 = getattr(row, 'E5')
    E6 = getattr(row, 'E6')
    E7 = getattr(row, 'E7')
    E8 = getattr(row, 'E8')
    E9 = getattr(row, 'E9')
    
    # PositionArray = [E0, E1, E2, E3, E4, E5, E6, E7, E8, E9]
    PositionArray = []
    for i in xrange(n_of_elevators):
      PositionArray .append([])
      PositionArray [i].append('E' + str(i))
      PositionArray [i].append(vars()['E' + str(i)])

    return render_to_response('bosslift/simulation.html', {'elevators': range(n_of_elevators), 'PositionArray': PositionArray })

def simulationtable(request):
    row0 =ConnectionSetup.objects.all().order_by('-id')[:1].get()
    n_of_elevators = getattr(row0, 'elevators')
    n_of_floors = getattr(row0, 'floors')

    row = ElevatorPosition.objects.all().order_by('-id')[:1].get()
    E0 = getattr(row, 'E0')
    E1 = getattr(row, 'E1')
    E2 = getattr(row, 'E2')
    E3 = getattr(row, 'E3')
    E4 = getattr(row, 'E4')
    E5 = getattr(row, 'E5')
    E6 = getattr(row, 'E6')
    E7 = getattr(row, 'E7')
    E8 = getattr(row, 'E8')
    E9 = getattr(row, 'E9')
    
    # PositionArray = [E0, E1, E2, E3, E4, E5, E6, E7, E8, E9]
    PositionArray = []
    for i in xrange(n_of_elevators):
      PositionArray .append([])
      PositionArray [i].append('E' + str(i))
      PositionArray [i].append(vars()['E' + str(i)])

    return render_to_response('bosslift/simulationtable.html', {'elevators': range(n_of_elevators), 'floors': range(n_of_floors), 'PositionArray': PositionArray })


@csrf_exempt
def elevatorstatusdb(request):
    row2 = ElevatorStatus.objects.all()
    row2.delete();

    E0 = request.POST['E0']
    E1 = request.POST['E1']
    E2 = request.POST['E2']
    E3 = request.POST['E3']
    E4 = request.POST['E4']
    E5 = request.POST['E5']
    E6 = request.POST['E6']
    E7 = request.POST['E7']
    E8 = request.POST['E8']
    E9 = request.POST['E9']
    p = ElevatorStatus(E0=E0, E1=E1, E2=E2, E3=E3, E4=E4, E5=E5, E6=E6, E7=E7, E8=E8, E9=E9);
    p.save();
    return HttpResponse("Elevator Positions have been updated.")


def elevatorstatus(request):
    row0 =ConnectionSetup.objects.all().order_by('-id')[:1].get()
    n_of_elevators = getattr(row0, 'elevators')

    row = ElevatorStatus.objects.all().order_by('-id')[:1].get()
    E0 = getattr(row, 'E0')
    E1 = getattr(row, 'E1')
    E2 = getattr(row, 'E2')
    E3 = getattr(row, 'E3')
    E4 = getattr(row, 'E4')
    E5 = getattr(row, 'E5')
    E6 = getattr(row, 'E6')
    E7 = getattr(row, 'E7')
    E8 = getattr(row, 'E8')
    E9 = getattr(row, 'E9')
    
    	
    # StatusArray = [E0, E1, E2, E3, E4, E5, E6, E7, E8, E9]
    StatusArray = []
    for i in xrange(n_of_elevators):
      StatusArray.append([])
      StatusArray[i].append('Elevator ' + str(i))
      StatusArray[i].append(vars()['E' + str(i)])


    return render_to_response('bosslift/elevatorstatus.html', {'elevators': range(n_of_elevators), 'StatusArray': StatusArray })



@csrf_exempt
def elevatorpositiondb(request):

    row2 = ElevatorPosition.objects.all()
    row2.delete();

    E0 = request.POST['E0']
    E1 = request.POST['E1']
    E2 = request.POST['E2']
    E3 = request.POST['E3']
    E4 = request.POST['E4']
    E5 = request.POST['E5']
    E6 = request.POST['E6']
    E7 = request.POST['E7']
    E8 = request.POST['E8']
    E9 = request.POST['E9']
    p = ElevatorPosition(E0=E0, E1=E1, E2=E2, E3=E3, E4=E4, E5=E5, E6=E6, E7=E7, E8=E8, E9=E9);
    p.save();
    return HttpResponse("Elevator Status has been updated.")

def elevatorposition(request):
    row0 =ConnectionSetup.objects.all().order_by('-id')[:1].get()
    n_of_elevators = getattr(row0, 'elevators')

    row = ElevatorPosition.objects.all().order_by('-id')[:1].get()
    E0 = getattr(row, 'E0')
    E1 = getattr(row, 'E1')
    E2 = getattr(row, 'E2')
    E3 = getattr(row, 'E3')
    E4 = getattr(row, 'E4')
    E5 = getattr(row, 'E5')
    E6 = getattr(row, 'E6')
    E7 = getattr(row, 'E7')
    E8 = getattr(row, 'E8')
    E9 = getattr(row, 'E9')
    
    	
    # PositionArray = [E0, E1, E2, E3, E4, E5, E6, E7, E8, E9]
    PositionArray = []
    for i in xrange(n_of_elevators):
      PositionArray .append([])
      PositionArray [i].append('Elevator ' + str(i))
      PositionArray [i].append(vars()['E' + str(i)])


    return render_to_response('bosslift/elevatorposition.html', {'elevators': range(n_of_elevators), 'PositionArray': PositionArray })
