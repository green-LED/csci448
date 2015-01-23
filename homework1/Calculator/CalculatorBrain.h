//
//  CalculatorBrain.h
//  Calculator
//
//  Created by Matthew Wichmann on 1/20/15.
//  Copyright (c) 2015 mwichmann. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CalculatorBrain : NSObject

- (void)pushOperand:(double)operand;
- (double)performOperation:(NSString*)operation;


@end
