################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/CompIntelCPP.cpp 

OBJS += \
./src/CompIntelCPP.o 

CPP_DEPS += \
./src/CompIntelCPP.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I"C:\MinGW\lib\gcc\mingw32\4.6.2\include\c++" -I"C:\MinGW\lib\gcc\mingw32\4.6.2\include" -I"C:\MinGW\include" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


