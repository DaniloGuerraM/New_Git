using AlumnosManagement.Application.Interfaces;
using AlumnosManagement.Infrastructure.Repositories;
using AlumnosManagement.Infrastructure.Data;
using Microsoft.EntityFrameworkCore;


var builder = WebApplication.CreateBuilder(args);

var connectioString = builder.Configuration.GetConnectionString("DefaultConnection");//

builder.Services.AddDbContext<ApplicationDbContext>(options => options.UseNpgsql(connectioString));


//builder.Services.AddScoped<IAlumnoRepository, AlumnoRepository>();
//builder.Services.AddDbContext<ApplicationDbContext>(options => options.UseSqlServer(builder.configuration.GetConnectioString("DefaultConnection")));

// Add services to the container.
builder.Services.AddControllers();

// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddScoped<IAlumnoRepository, AlumnoRepository>();


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


