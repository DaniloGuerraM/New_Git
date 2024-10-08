using AlumnosManagement.Application.Interfaces;
using AlumnosManagement.Infrastructure.Repositories;
using AlumnosManagement.Infrastructure.Data;
using Microsoft.EntityFrameworkCore;
using AlumnosManagement.Application;


var builder = WebApplication.CreateBuilder(args);

var connectioString = builder.Configuration.GetConnectionString("DefaultConnection");//

builder.Services.AddScoped<IAlumnoRepository, AlumnoRepository>();
builder.Services.AddScoped<IAlumnoServicio, AlumnoServicio>();


builder.Services.AddDbContext<ApplicationDbContext>(options => options.UseNpgsql(connectioString));


// Add services to the container.
builder.Services.AddControllers();


builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();



var app = builder.Build();

// Configure the HTTP request pipeline.

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}


app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();


