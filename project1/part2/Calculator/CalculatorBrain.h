//
//  CalculatorBrain.h
//  Calculator
//
//  Created by Matthew Wichmann on 1/20/15.
//  Copyright (c) 2015 mwichmann. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CalculatorBrain : NSObject

@property (readonly) id program;

- (void)pushOperand:(double)operand;
- (void)pushVariable:(NSString *) variable;
- (void)pushOperation:(NSString *) operation;

- (void)clearPrograms;

+ (NSString *)descriptionOfProgram:(id)program;

+ (id)runProgram:(id)program;
+ (id)runProgram:(id)program
usingVariableValues:(NSDictionary *)variableValue;

@end
