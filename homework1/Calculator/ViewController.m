//
//  ViewController.m
//  Calculator
//
//  Created by Matthew Wichmann on 1/18/15.
//  Copyright (c) 2015 mwichmann. All rights reserved.
//

#import "ViewController.h"
#import "CalculatorBrain.h"

@interface ViewController ()
@property (nonatomic) BOOL userIsEntering;
@property (nonatomic, strong) CalculatorBrain *brain;
@end

@implementation ViewController

@synthesize display;
@synthesize userIsEntering;
@synthesize brain = _brain;

- (CalculatorBrain *)brain {
    if (!_brain) _brain = [[CalculatorBrain alloc] init];
    return _brain;
}

- (IBAction)digitPressed:(UIButton *)sender {
    NSString *digit = [sender currentTitle];
    if (self.userIsEntering) {
        self.display.text = [self.display.text stringByAppendingString:digit];
    } else {
        self.display.text = digit;
        self.userIsEntering = YES;
    }
}

- (IBAction)enterPressed {
    [self.brain pushOperand:[self.display.text doubleValue]];
    self.userIsEntering = NO;
}

- (IBAction)operationPressed:(id)sender {
    if (userIsEntering) {
        [self enterPressed];
    }
    NSString *operation = [sender currentTitle];
    double result = [self.brain performOperation:operation];
    self.display.text = [NSString stringWithFormat:@"%g", result];
}

@end
