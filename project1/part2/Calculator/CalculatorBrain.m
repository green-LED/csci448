//
//  CalculatorBrain.h
//  Calculator
//
//  Created by CS193p Instructor.
//  Copyright (c) 2011 Stanford University.
//  All rights reserved.
//

#import "CalculatorBrain.h"

@interface CalculatorBrain()

@property (nonatomic, strong) NSMutableArray *programStack;


@end

@implementation CalculatorBrain

@synthesize programStack = _programStack;

- (NSMutableArray *)programStack {
	if (_programStack == nil) _programStack = [[NSMutableArray alloc] init];
	return _programStack;
}


+ (NSString *)descriptionOfProgram:(id)program {	
	
	
	// TODO: write however you would like to display the sequence of operands, variables, operations on stack
    
    return @"implement this";
}

+ (id)popOperandOffProgramStack:(NSMutableArray *) stack {
	
    double result = 0;
	
	id topOfStack = [stack lastObject];
	if (topOfStack) [stack removeLastObject]; else return @"0";
	
	if ([topOfStack isKindOfClass:[NSNumber class]]) return topOfStack;
	
	NSString *operation = topOfStack;
	
	if ([operation isEqualToString:@"π"]) { 
		result = M_PI;
	}
    
    // TODO: similarly, handle all other operations, including sin, cos, +, -, etc. (hint: see how many operands each operator needs).
    
    return [NSNumber numberWithDouble:result];
}

- (void)pushOperation:(NSString *) operation {
    [self.programStack addObject:operation];
}

+ (id)runProgram:(id)program {
	// Call the new runProgram method with a nil dictionary
	return [self runProgram:program usingVariableValues:nil];
}

+ (BOOL)isOperation:(NSString *)operation {
    
    //TODO: Check to see if it's a valid operation.
    
    return YES;
}

+ (id)runProgram:(id)program 
usingVariableValues:(NSDictionary *)variableValues {
	
	
	NSMutableArray *stack= [program mutableCopy];
	
	// For each item in the program
	for (int i=0; i < [stack count]; i++) {
		id obj = [stack objectAtIndex:i]; 
		
		// See whether we think the item is a variable
		if ([obj isKindOfClass:[NSString class]] && ![self isOperation:obj]) {	
			id value = [variableValues objectForKey:obj];			
			// If value is not an instance of NSNumber, set it to zero
			if (![value isKindOfClass:[NSNumber class]]) {
				value = [NSNumber numberWithInt:0];
			}			
			// Replace program variable with value.
			[stack replaceObjectAtIndex:i withObject:value];
		}		
	}	
	// Starting popping off the stack
	return [self popOperandOffProgramStack:stack];	
}


@end
