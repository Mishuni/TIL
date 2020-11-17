# Review of Paper

## Federated Learning in Mobile Edge Networks: A Comprehensive Survey

##### IEEE COMMUNICATIONS SURVEYS & TUTORIALS, VOL. 22, NO. 3, THIRD QUARTER 2020

##### Wei Yang Bryan Lim , Nguyen Cong Luong, Dinh Thai Hoang , Member, IEEE, Yutao Jiao ,Ying-Chang Liang , Fellow, IEEE, Qiang Yang , Fellow, IEEE, Dusit Niyato , Fellow, IEEE, and Chunyan Miao , Senior Member, IEEE





###### 3. Communication Cost

1. Edge and End Computation
   * reduce the number of communication rounds whereas increase the time of computation 
     *  (i) increasing parallelism in which more participants are selected to participate in each round of training and 
     * (ii) increasing computation per participant whereby each participant performs more local updates before communication for global aggregation.
2. Model Compression
   *  Model or gradient compression involves the communication of an update that is transformed to be more compact, e.g., through sparsification, quantization or subsampling [93], rather than the communication of full update
   * However, since the compression may introduce noise, the objective is to reduce the size of update transferred during each round while maintaining the quality of trained models
3. Importance-based  Updating
   * update the model that is more important and remove other less-important models 
   * In fact, besides saving on communication costs, omitting some updates from participants can even improve the global model performance.
     * the authors in [95] propose the edge Stochastic Gradient Descent (eSGD) algorithm that selects only a small fraction of important gradients to be communicated to the FL server for parameter update during each communication round.

> However, many of the approaches, e.g., model compression, result in a deterioration in model accuracy or incur high computation cost. For example, when too many local updates are implemented between communication rounds, the communication cost is indeed reduced but the convergence can be significantly delayed [98]. The tradeoff between these sacrifices and communication cost reduction thus has to be well-managed.



###### 4. Resource Allocation

> FL involves the participation of heterogeneous devices that have different dataset qualities, computation capabilities, energy states, and willingness to participate.

1. Participant Selection
   * related to how to select the participant devices in each training round
     * randomly selection : the training progress of FL is limited by the training time of the slowest participating devices,
     * New participant selection protocols are thus investigated to address the training bottleneck in FL.
2. Joint Radio and Computation Resource Management
   * Even though computation capabilities of mobile devices have grown rapidly, many devices still face a scarcity of radio resources
   * Given that local model transmission is an integral part of FL, there has been a growing number of studies that focus on developing novel wireless communication techniques for efficient FL.
3. Adaptive Aggregation
   * The conventional approach to global aggregation is a synchronous one, i.e., global aggregations occur in fixed intervals after all participants complete a certain number of rounds of local computation.
   * However, adaptive calibrations of global aggregation frequency can be investigated to increase training efficiency subject to resource constraints.
4. Incentive Mechanism
   * In the practical implementation of FL, participants may be reluctant to participate in a federation without receiving compensation since training models is resource-consuming.
   * In addition, there exists information asymmetry between the FL server and participants since participants have greater knowledge of their available computation resources and data quality. 
   * Therefore, incentive mechanisms have to be carefully designed to both reward participation and reduce the potential adverse impacts of information asymmetry