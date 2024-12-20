using Asistencia.Application;
using Asistencia.Application.Interfaces;
using Asistencia.Infrastructure.Data;
using Asistencia.Infrastructure.Repository;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

var connectioString = builder.Configuration.GetConnectionString("DefaultConnection");



builder.Services.AddScoped<IControlQRServicio, ControlQRServicio>();
builder.Services.AddScoped<IControlQRRepository, ControlQRRepository>();


builder.Services.AddScoped<IEventServicio, EventServicio>();
builder.Services.AddScoped<IEventRepocitorio, EventRepocitorio>();

builder.Services.AddDbContext<ApplicationDbContext>(options => options.UseNpgsql(connectioString));

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();

builder.Services.AddCors(p => p.AddPolicy("corsapp",builder => {
    builder.WithOrigins("*")
    .AllowAnyMethod()
    .AllowAnyHeader();
}));


builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseCors("corsapp");
app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();
app.Run();

