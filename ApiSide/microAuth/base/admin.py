from django.contrib import admin
from .models import *
from django import forms

class UserModelAdmin(admin.ModelAdmin):
    list_display = ('id', 'first_name', 'email', 'last_name', 'username')
    search_fields = ('username', 'email')

admin.site.register(User, UserModelAdmin)