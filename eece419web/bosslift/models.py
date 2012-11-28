from django.db import models

class RxRequest(models.Model):
    request = models.CharField(max_length=200)
    date = models.DateTimeField('date received')
    def __unicode__(self):
        return self.request


class ConnectionSetup(models.Model):
    address = models.CharField(max_length=200)
    elevators = models.IntegerField()
    floors = models.IntegerField()
    date = models.DateTimeField('date of connection')
    def __unicode__(self):
        return self.address

class ElevatorStatus(models.Model):
    E0 = models.CharField(max_length=200)
    E1 = models.CharField(max_length=200)
    E2 = models.CharField(max_length=200)
    E3 = models.CharField(max_length=200)
    E4 = models.CharField(max_length=200)
    E5 = models.CharField(max_length=200)
    E6 = models.CharField(max_length=200)
    E7 = models.CharField(max_length=200)
    E8 = models.CharField(max_length=200)
    E9	 = models.CharField(max_length=200)
    def __unicode__(self):
        return self.E0

class ElevatorPosition(models.Model):
    E0 = models.CharField(max_length=200)
    E1 = models.CharField(max_length=200)
    E2 = models.CharField(max_length=200)
    E3 = models.CharField(max_length=200)
    E4 = models.CharField(max_length=200)
    E5 = models.CharField(max_length=200)
    E6 = models.CharField(max_length=200)
    E7 = models.CharField(max_length=200)
    E8 = models.CharField(max_length=200)
    E9	 = models.CharField(max_length=200)
    def __unicode__(self):
        return self.E0

