//
//  ViewController.m
//  Calculator
//
//  Created by Matthew Wichmann on 1/18/15.
//  Copyright (c) 2015 mwichmann. All rights reserved.
//

#import "ViewController.h"
#import "CalculatorBrain.h"
#import "GraphViewController.h"

@interface ViewController ()
@property (nonatomic) BOOL userIsEntering;
@property (nonatomic, strong) CalculatorBrain *brain;
@property (nonatomic, strong) NSDictionary *variableValue;
@property (nonatomic, weak) id <ControllerDelegate> popoverDelegate;
@end

@implementation ViewController

@synthesize display;
@synthesize history;
@synthesize userIsEntering;
@synthesize brain = _brain;
@synthesize variableValue = _variableValue;

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation {
    return self.splitViewController ?
    YES : UIInterfaceOrientationIsPortrait(toInterfaceOrientation);
}

- (GraphViewController *)graphViewController {
    return self.popoverDelegate ?
    self.popoverDelegate :[self.splitViewController.viewControllers lastObject];
}

- (CalculatorBrain *)brain {
    //if (self.popoverDelegate) _brain = [[self.popoverDelegate delegateController] brain];
    if (!_brain) _brain = [[CalculatorBrain alloc] init];
    return _brain;
}

- (NSDictionary *)variableValue {
    
    if (!_variableValue) _variableValue = [[NSDictionary alloc] init];
    return _variableValue;
}


-(void)updateView {
    
    // Find the result by running the program passing in the test variable values
    id result = [CalculatorBrain runProgram:self.brain.program
                        usingVariableValues:self.variableValue];
    
    self.display.text = result;
    self.history.text = [CalculatorBrain descriptionOfProgram:self.brain.program];
    
    self.userIsEntering = NO;
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

- (IBAction)dotpressed {
    if (self.userIsEntering) {
        if (![self.display.text containsString:@"."]) {
            self.display.text = [self.display.text stringByAppendingString:@"."];
        }
    } else {
        self.display.text = @"0.";
        self.userIsEntering = YES;
    }
}

- (IBAction)enterPressed {
    [self.brain pushOperand:[self.display.text doubleValue]];
    [self updateView];
}

- (IBAction)operationPressed:(UIButton *)sender {
    if (userIsEntering) {
        [self enterPressed];
    }
    [self.brain pushOperation:[sender currentTitle]];
    [self updateView];
}

- (IBAction)variablePressed:(UIButton *)sender {
    [self.brain pushVariable:[sender currentTitle]];
    [self updateView];
}

- (IBAction)clearAll {
    [self.brain clearPrograms];
    self.userIsEntering = NO;
    self.display.text = @"0";
    self.history.text = @"";
}

// undo last digit (or dot) pressed
// If last digit is erased, stop entering
- (IBAction)backspace {
    if (self.userIsEntering) {
        self.display.text = [self.display.text substringToIndex:self.display.text.length - 1];
        if (self.display.text.length == 0) {
            self.userIsEntering = NO;
            self.display.text = @"0";
        }
    }
}

- (IBAction)drawGraphPressed {
    
    if ([self graphViewController]) {
        [[self graphViewController] setProgram:self.brain.program];
        [[self graphViewController] refreshView ];
    } else {
        [self performSegueWithIdentifier:@"DisplayGraphView" sender:self];
    }
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    [segue.destinationViewController setProgram:self.brain.program];
}


- (void)viewDidAppear:(BOOL)animated {
    [self updateView];
}

- (void)viewDidUnload {
    [super viewDidUnload];
}


@end
